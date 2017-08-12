describe('TransactionModel', function() {

  describe('basic', function() {

    it('should has default original field & relations', function (done) {
      Promise
        .resolve()
        .then(function () {
          return User
            .create({ 
              name:'merchant',  
              balance:'10000',  
              email: 'merchant@email.domain',  
              password: 'blabla'
            })
        })
        .then(function (merchant) {
          buyer = User
            .create({ 
              name:'buyer',  
              balance:'10000',  
              email: 'buyer@email.domain',  
              password: 'blabla'
            })
          item = Item
            .create({ 
              name:'item_2',  
              merchant: merchant,  
              price: 500,  
              image: 'some-img-url.jpg',  
              description: 'blabla'
            })
          return Promise.all([buyer, item])
        })
        .then(function (result) {
          return Transaction.create({
            customer: result[0],
            item: result[1],
            amount: 3
          })
        })
        .then(function (transaction) {
          assert('transaction_id' in transaction, 'transaction_id field doesn\'t exist' )
          assert('customer' in transaction, 'customer field doesn\'t exist' )
          assert('item' in transaction, 'item field doesn\'t exist' )
          assert('amount' in transaction, 'amount field doesn\'t exist' )
          assert('date_time' in transaction, 'date_time field doesn\'t exist' )
          p1 = transaction.get_customer()
          p2 = transaction.get_item()
          p3 = transaction.get_merchant()
          p0 = Transaction.destroy(transaction)
          return Promise.all([p1, p2, p3, p0])
        })
        .then(function (promises) {
          assert(promises[0].name == 'buyer')
          assert(promises[1].name == 'item_2')
          assert(promises[2].name == 'merchant')
          assert(promises[2].balance == promises[0].balance + promises[1].price * promises[3][0].amount * 2 )
          return Promise.all([
              User.destroy(promises[0]),
              Item.destroy(promises[1]),
              User.destroy(promises[2])
            ])
        })
        .then(function () { done() })
        .catch(done)
    })

  })

})