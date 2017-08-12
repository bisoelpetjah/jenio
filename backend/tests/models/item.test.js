describe('ItemModel', function() {

  describe('basic', function() {

    it('should has default original field', function (done) {
      Promise
        .resolve()
        .then(function () {
          return User
            .create({ 
              name:'merchant',  
              balance: 1000,  
              email: 'merchant@email.domain',  
              password: 'blabla'
            })
        })
        .then(function (user) {
          return Item
            .create({ 
              name: 'item_1',  
              merchant: user,  
              price: 500,  
              image: 'some-img-url.jpg',  
              description: 'blabla'
            })
        })
        .then(function (item) {
          assert('item_id' in item, 'item_id field doesn\'t exist' )
          assert('name' in item, 'name field doesn\'t exist' )
          assert('merchant' in item, 'merchant field doesn\'t exist' )
          assert('price' in item, 'price field doesn\'t exist' )
          assert('image' in item, 'image field doesn\'t exist' )
          assert('description' in item, 'description field doesn\'t exist' )
          assert('stock' in item, 'stock field doesn\'t exist' )
          assert('date_time' in item, 'date_time field doesn\'t exist' )
          p0 = Item.destroy(item)
          p1 = item.get_merchant()
          return Promise.all([p0, p1])
        })
        .then(function (proms) {
          assert(proms[1].name == 'merchant')
          return User.destroy(proms[1])
        })
        .then(function () { done() })
        .catch(done)
    })

  })

})