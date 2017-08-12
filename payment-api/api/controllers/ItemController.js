/**
 * ItemController
 *
 * @description :: Server-side logic for managing items
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {

  view: function(req, res) {
    id = req.params['id']
    Item
      .findOne({'id': id})
      .then(function (result) {
        res.view('item', {item: result})
      })
  },

  create_page: function(req, res) {
    res.view('item_create')
  },

  get_from_uuid: function(req, res) {
    item_id = req.params['item_id']
    Item
      .findOne({'item_id': item_id})
      .populate('merchant')
      .then(function (result) {
        res.json(result)
      })
      .catch(console.log)
  }
}

