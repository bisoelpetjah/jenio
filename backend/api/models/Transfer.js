/**
 * Transfer.js
 *
 * @description :: TODO: Uang yang dikirim dari sender ke receiver
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    
    transfer_id: {
      type: 'string',
      defaultsTo: function() {
        return uuid.v4()
      }
    },

    sender: {
      required: true,
      model: 'user'
    },

    receiver: {
      required: true,
      model: 'user'
    },

    amount: {
      type: 'integer',
      required: true
    },

    date_time: {
      type: 'datetime',
      required: false,
      defaultsTo: function() {
        return moment().toDate()
      }
    },

    get_sender: function () {
      return User.findOne(this.sender)
    },

    get_receiver: function () {
      return User.findOne(this.receiver)
    },

  },

  afterCreate: function (val, cb) {
    p0 = User
      .findOne(val.sender)
      .then(function (sender) {
        return User
          .update(val.sender, {balance: sender.balance - val.amount})
      })
    p1 = User
      .findOne(val.receiver)
      .then(function (receiver) {
        return User
          .update(val.receiver, {balance: receiver.balance + val.amount})
      })
    Promise
      .all([p0, p1])
      .then(function (proms) { cb() })
  }

}

