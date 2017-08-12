describe('UserModel', function() {

  describe('basic', function() {

    it('should has default original field', function (done) {
      User
        .create({ 
          name:'user_1',  
          email: 'username@email.domain',  
          password: 'blabla'
        })
        .then(function (user) {
          assert('id' in user, 'id field doesn\'t exist' )
          assert('name' in user, 'name field doesn\'t exist' )
          assert('image' in user, 'image field doesn\'t exist' )
          assert('email' in user, 'email field doesn\'t exist' )
          assert('password' in user, 'password field doesn\'t exist' )
          assert('balance' in user, 'balance field doesn\'t exist' )
          assert(user.balance == 0)
          return User.destroy(user)
        })
        .then(function () { done() })
        .catch(done)
    })

    it('should has relation with items', function (done) {
      Promise
        .resolve()
        .then(function () {
          return User
            .create({ 
              name:'user_2',  
              balance: 1000,  
              email: 'merchant@email.domain',  
              password: 'blabla'
            })
        })
        .then(function (user) {
           item1 = Item
            .create({ 
              name: 'item1',  
              merchant: user,  
              price: 500,  
              image: 'some-img-url.jpg',  
              description: 'blabla'
            })
           item2 = Item
            .create({ 
              name: 'item2',  
              merchant: user,  
              price: 500,  
              image: 'some-img-url.jpg',  
              description: 'blabla2'
            })
          return Promise.all([item1, item2])
        })
        .then(function (result) {
          Item
            .findOne(result[0])
            .then(function (item) {
              return item.get_merchant()
            })
            .then(function (merchant) {
              return merchant.get_items()
            })
            .then(function (items) {
              assert(items.length == 2)
              // assert(items[0].name == 'item1')
              // assert(items[1].name == 'item2')
              return Promise.all([items[0], items[1], items[0].get_merchant()])
            })
            .then(function (proms) {
              p0 = Item.destroy(proms[0])
              p1 = Item.destroy(proms[1])
              p2 = User.destroy(proms[2])
              return Promise.all([p0, p1, p2])
            })
            .then(function () { done() })
        })
        .catch(done)
    })
  })

})