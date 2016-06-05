'use strict'

require('dotenv').config();

const http = require('http')
// const express = require('express')
const Bot = require('messenger-bot')
const async = require('asyncawait/async')
const await = require('asyncawait/await')

const models = require("./models")
const _ = require("lodash")

var bot = new Bot({
  token: process.env.TOKEN,
  verify: process.env.VERIFY_TOKEN,
  app_secret: process.env.APP_SECRET
})

bot.on('error', (err) => {
  console.log(err.message)
})

bot.on('message', async((payload, reply) => {
  console.log("message_start");
  let profile = await(new Promise((resolve, reject) => {
    bot.getProfile(payload.sender.id, (err, res) => {
      resolve(res)
    })
  }))

  let items = await(models.Item.findAll({
    where: {
      title: {
        $like: `%${payload.message.text}%`
      }
    },
    order: [
      models.sequelize.fn( 'RAND' )
    ]
  }))

  let elements = _.take(_.map(items, (item) => {
    return {
      title: item.dataValues.title,
      image_url: item.dataValues.image,
      buttons: [{
        type: "web_url",
        title: "구경하러 가기",
        url: item.dataValues.url
      }]
    }
  }), 3)

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
