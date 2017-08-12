/**
 * Transaction.js
 *
 * @description :: TODO: You might write a short summary of how this model works and what it represents here.
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    
    transaction_id: {
      type: 'string',
      defaultsTo: function() {
        return uuid.v4()
      }
    },

    customer: {
      required: true,
      model: 'user'
    },

    item: {
      required: true,
      model: 'item'
    },

    amount: {
      type: 'integer',
      defaultsTo: 1
    },

    date_time: {
      type: 'datetime',
      required: false,
      defaultsTo: function() {
        return moment().toDate()
      }
    },

    get_customer: function () {
      return User.findOne(this.customer)
    },

    get_item: function () {
      return Item.findOne(this.item)
    },

    get_merchant: function () {
      return Item
        .findOne(this.item)
        .then(function (item) {
          return item.get_merchant()
        })
    }
  },

  afterCreate: function (val, cb) {
    Transaction
      .findOne(val)
      .then(function (trans) {
        p1 = trans.get_merchant()
        p2 = trans.get_customer()
        p3 = trans.get_item()
        return Promise.all([p1, p2, p3])
      })
      .then(function (proms) {
        p0 = Transfer.create({
          receiver: proms[0],
          sender: proms[1],
          amount: proms[2].price * val.amount
        })
        p1 = Item
          .update(proms[2].id, {stock: proms[2].stock - val.amount})
          .catch((e) => console.log(e.message))
        return Promise.all([p0, p1])
      })
      .then(function (p) {
        cb()
      })
  }
}

