/**
 * TransactionController
 *
 * @description :: Server-side logic for managing transactions
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {

  create: function(req, res) {
    item_uuid = req.body['item']
    customer_id = req.body['customer']
    amount = req.body['amount']
    return Promise
      .resolve()
      .then(function () {
        return Item
          .findOne({item_id: item_uuid})
      })
      .then(function (item) {
        return Transaction
          .create({
            customer: customer_id,
            item: item.id,
            amount: amount
          })
      })
      .then(function (transaction) {
        res.json({})
      })
      .catch((e) => {
        res.json({'error':e.message})
      })
  },

  createOffline: function(req, res) {
    token = req.body['token']
    signed_token = req.body['signed_token']
    return Promise
      .resolve()
      .then(() => {
        // ...
        console.log(token, signed_token)
      })
      .then((result) => {
        res.json({})
      })
      .catch((e) => {
        res.json({'error':e.message})
      })
  }
}

