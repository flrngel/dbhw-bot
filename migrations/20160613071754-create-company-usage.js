'use strict';
module.exports = {
  up: function(queryInterface, Sequelize) {
    return queryInterface.createTable('CompanyUsages', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      company_id: {
        type: Sequelize.INTEGER,
        references: {
          model: "Companies",
          key: "id"
        },
        onUpdate: 'cascade',
        onDelete: 'cascade'
      },
      keyword: {
        type: Sequelize.STRING
      },
      bid_price: {
        type: Sequelize.INTEGER
      },
      keyword: {
        type: Sequelize.STRING
      },
      title: {
        type: Sequelize.STRING
      },
      image_url: {
        type: Sequelize.STRING
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },
  down: function(queryInterface, Sequelize) {
    return queryInterface.dropTable('CompanyUsages');
  }
};
