const crypto = require('crypto')

module.exports = {

  generateRandomBytes: (size) => {
    return crypto.randomBytes(size)
  },

  generateRandomNumber: (size) => {
    const r = []

    for (let i = 0; i < size; i++) {
      r.push(Math.floor(Math.random() * (9 - 0)) + 0)
    }

    return r
  },

  computeSignedToken: (secret, tokenWithNonce) => {
    const hmac = crypto.createHmac('sha256', secret)
    hmac.update(tokenWithNonce)

    return hmac.digest()
  }

}
