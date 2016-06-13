'use strict';
module.exports = function(sequelize, DataTypes) {
  var CompanyUsage = sequelize.define('CompanyUsage', {
    company_id: DataTypes.INTEGER,
    keyword: DataTypes.STRING,
    bid_price: DataTypes.INTEGER
    url: DataTypes.INTEGER
    image_url: DataTypes.INTEGER
  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return CompanyUsage;
};
