/**
 * TransactionController
 *
 * @description :: Server-side logic for managing transactions
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

const getUserSharedsecret = (userId) => {
  return 'RL722DYux4ZIJ0/oIm0Uw51nQ+ofXPtjMjYn7IJksCfz2+Tz9NVV0JerfAzzHD/v1Hxpz+sGeiw/HJ89XQjpbHILm+saLN9Xom0eIMpxVexbthG/YZ3g+ZQmcLCP/pKde3XuzyCwLDdzkPwYfLVP9ElQH07RMBlHfJk0M2nAx44='
}

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
    return Promise
      .resolve()
      .then(() => {
        const tokenUtf8 = req.body['token']
        const responseBase64 = req.body['signed_token']
        const merchantId = req.body['merchant_id']
        const amount = req.body['amount']

        // TODO: check if token is valid [?]

        const response = Buffer.from(responseBase64, 'base64')
        const signedToken = response.slice(0, 32)
        const nonce = response.slice(32, 36)
        const userId = response.slice(36, response.length).toString('utf8')

        // TODO: check if userId is valid

        const secretBase64 = getUserSharedsecret()

        const secret = Buffer.from(secretBase64, 'base64')

        const token = Buffer.from(tokenUtf8, 'utf8')
        const tokenWithNonce = Buffer.concat([ token, nonce ])

        const computedSignedToken = CryptoService.computeSignedToken(secret, tokenWithNonce)

        if (signedToken.compare(computedSignedToken) === 0) {
          return { userId, merchantId, amount }
        } else {
          return Promise.reject({ message: 'fail when creating offline payment.'})
        }
      })
      .then(({ userId, merchantId, amount }) => {
        return Transfer.create({
          sender: userId,
          receiver: merchantId,
          amount
        })
      })
      .then(({ amount }) => {
        res.json({ success: 'success.', amount })
      })
      .catch((e) => {
        res.status(400).json({ 'error': e.message })
      })
  }
}

