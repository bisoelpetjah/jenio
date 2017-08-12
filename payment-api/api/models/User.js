/**
 * User.js
 *
 * @description :: Pembeli, ada informasi pribadi, alamat
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    name: {
      type: 'string',
      required: true,
    },

    image: {
      type: 'string',
      required: false,
      defaultsTo: 'http://tr3.cbsistatic.com/fly/bundles/techrepubliccore/images/icons/standard/icon-user-default.png'
    },

    email: {
      type: 'string',
      required: true,
    },

    password: {
      type: 'string',
      required: true,
    },

    sharedsecret: {
      type: 'string',
      required: false,
    },

    balance: {
      type: 'integer',
      required: false,
      defaultsTo: 0,
    },

    get_items: function () {
      return Item.find({merchant: this.id})
    },

  }

}

