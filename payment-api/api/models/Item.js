/**
 * Item.js
 *
 * @description :: Barang yang akan diperjualbelikan, bisa single atau multiple.
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    item_id: {
      type: 'string',
      defaultsTo: function() {
        return uuid.v4()
      }
    },

    name: {
      type: 'string',
      required: true,
    },

    price: {
      type: 'integer',
      required: true,
    },

    image: {
      type: 'string',
      required: false,
    },

    qr: {
      type: 'string',
      required: false,
    },

    description: {
      type: 'string',
      required: false,
    },

    stock: {
      type: 'integer',
      required: false,
      defaultsTo: 100,
    },

    merchant: {
      required: true,
      model: 'user'
    },

    date_time: {
      type: 'datetime',
      required: false,
      defaultsTo: function() {
        return moment().toDate()
      }
    },

    get_merchant: function () {
      return User.findOne(this.merchant)
    },

  },

  beforeCreate: function (val, cb) {
    text = val.item_id
    url_data = {
      "TYPE": "text",
      "DATA": {
        "TEXT": text
      }
    }
    setting_data = {
      "LAYOUT": {
        "COLORBG": "ffffff",
        "GRADIENT_TYPE": "NO_GR",
        "COLOR1": "02a8e6"
      },
      "EYES": {
        "EYE_TYPE": "Spike",
        "COLOR_EHG": "028dce",
        "COLOR_EHD": "028dce",
        "COLOR_EBG": "028dce",
        "COLOR_IHG": "028dce",
        "COLOR_IHD": "028dce",
        "COLOR_IBG": "028dce"
      },
      "LOGO": {
        "L_NAME": "http://i.imgur.com/aL0DRs7.png",
        "EXCAVATE": true
      },
      "E": "H",
      "BODY_TYPE": 7
    }
    encoded_url = encodeURI('https://unitag-qr-code-generation.p.mashape.com/api?data='+JSON.stringify(url_data)+'&setting='+JSON.stringify(setting_data))
    var options = {
      url: encoded_url,
      headers: {'X-Mashape-Key': 'cwOW10sl3jmshdoYCSZwhgTrasAYp1DXoNcjsnS3N3hWsrXnfn'}
    }
    global_request
      .get(options, function (error, response, body) {
        if (!error && response.statusCode == 200) {
          val.qr = new Buffer(body).toString('base64')
        } else {
          val.qr = 'iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=='
        }
        cb()
    })
  }

}

