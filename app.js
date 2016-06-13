'use strict'

require('dotenv').config();

const request = require('request')
const http = require('http')
// const express = require('express')
const Bot = require('messenger-bot')
const async = require('asyncawait/async')
const await = require('asyncawait/await')

const models = require("./models")
const _ = require("lodash")

// Define relation
models.Company.hasMany(models.CompanyUsage)
models.CompanyUsage.belongsTo(models.Company)

// Bot init
var bot = new Bot({
  token: process.env.TOKEN,
  verify: process.env.VERIFY_TOKEN,
  app_secret: process.env.APP_SECRET
})

// Error
bot.on('error', (err) => {
  console.log(err.message)
})

// Main
bot.on('message', async((payload, reply) => {
  console.log("message_start");
  let profile = await(new Promise((resolve, reject) => {
    bot.getProfile(payload.sender.id, (err, res) => {
      resolve(res)
    })
  }))

  let keyword = payload.message.text

  let companies = await(models.Company.findAll())
  let hook_results = await(_.map(companies, (company) => {
    return new Proimse((resolve, reject) => {
      request({
        method: "GET",
        uri: company.hook_url,
        timeout: 3000,
        json: true
      }, (error, res, body) => {
        if ( error ) {
          resolve(null)
        }
        else {
          if ( body.bid === true ) {
            resolve({
              company_id: company.id,
              bid_price: body.price,
              keyword: keyword,
              image_url: body.image_url,
              url: body.url
            })
          }
          else {
            resolve(null)
          }
        }
      })
    })
  }))

  let filtered_hook_results = _.take(_.sortBy(_.reject(hook_results, (x) => x === null), (obj) => -parseInt(obj.bid_price)), 3)

  // insert usage
  async(_.each(filtered_hook_results, (obj) => models.CompanyUsage.create(obj)))

  let bidded_elements = _.map(filtered_hook_results, (item) => {
    return {
      title: item.title,
      image_url: item.image_url,
      buttons: [{
        type: "web_url",
        title: "구경하러 가기",
        url: item.url
      }]
    }
  })
  
  let placeholdings = await(models.Item.findAll({
    where: {
      title: {
        $like: `%${keyword}%`
      }
    },
    order: [
      models.sequelize.fn( 'RAND' )
    ]
  }))

  let placeholding_elements = _.take(_.map(placeholdings, (item) => {
    return {
      title: item.dataValues.title,
      image_url: item.dataValues.image,
      buttons: [{
        type: "web_url",
        title: "구경하러 가기",
        url: item.dataValues.url
      }]
    }
  }), 3 - filtered_hook_results)

  let elements = _.concat(bidded_elements, placeholding_elements)

  await(reply({attachment: {
    type: "template",
    payload: {
      template_type: "generic",
      elements: elements
    }
  }}))
}))

http.createServer(bot.middleware()).listen(9999)
console.log('Echo bot server running at port 9999.')
