var Sails = require('sails'),
chai  = require('chai'),
sails

global.request = require('supertest')
global.should  = chai.should()
global.assert  = chai.assert

before(function(done) {
  this.timeout(5000)

  Sails.lift({
    environment: 'test',
    port: 1337,
    models: {
      connection: 'test',
      migrate: 'drop'
    }
  }, function(err, server) {
    sails = server
    if (err) return done(err)
    done(err, sails)
  })
})

after(function(done) {
  Sails.lower(done)
})
