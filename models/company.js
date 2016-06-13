'use strict';
module.exports = function(sequelize, DataTypes) {
  var Company = sequelize.define('Company', {
    name: DataTypes.STRING,
    email: DataTypes.STRING,
    password: DataTypes.STRING,
    hook_url: DataTypes.STRING,
    api_token: DataTypes.STRING
  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return Company;
};
