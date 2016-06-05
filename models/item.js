'use strict';
module.exports = function(sequelize, DataTypes) {
  var Item = sequelize.define('Item', {
    title: DataTypes.TEXT,
    url: DataTypes.STRING,
    image: DataTypes.STRING,
    description: DataTypes.TEXT,
    keywords: DataTypes.TEXT,
  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return Item;
};
