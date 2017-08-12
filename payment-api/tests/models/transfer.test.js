describe('TransferModel', function() {

  describe('basic', function() {

    it('should has default original field & relations', function (done) {
      Promise
        .resolve()
        .then(function () {
          user = User
            .create({ 
              name:'sender',  
              email: 'username@email.domain',  
              balance: 1000,
              password: 'blabla'
            })
          user2 = User
            .create({ 
              name:'receiver',  
              email: 'username2@email.domain',  
              balance: 1000,
              password: 'blabla'
            })
          return Promise.all([user, user2])
        })
        .then(function (result) {
          return Transfer.create({
            sender: result[0],
            receiver: result[1],
            amount: 200
          })
        })
        .then(function (transfer) {
          assert('transfer_id' in transfer, 'transfer_id field doesn\'t exist' )
          assert('sender' in transfer, 'sender field doesn\'t exist' )
          assert('receiver' in transfer, 'receiver field doesn\'t exist' )
          assert('amount' in transfer, 'amount field doesn\'t exist' )
          assert('date_time' in transfer, 'date_time field doesn\'t exist' )
          p1 = transfer.get_sender()
          p2 = transfer.get_receiver()
          p0 = Transfer.destroy(transfer)
          return Promise.all([p1, p2, p0])
        })
        .then(function (promises) {
          assert(promises[0].name == 'sender')
          assert(promises[0].balance == 800)
          assert(promises[1].name == 'receiver')
          assert(promises[1].balance == 1200)
          p0 = User.destroy(promises[0])
          p1 = User.destroy(promises[1])
          return Promise.all([p0, p1])
        })
        .then(function () { done() })
        .catch(done)
    })

  })

})