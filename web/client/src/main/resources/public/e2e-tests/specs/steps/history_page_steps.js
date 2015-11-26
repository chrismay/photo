(function() {

  'use strict';
  //http://chaijs.com/
  var chai = require('chai');

  //https://github.com/domenic/chai-as-promised/
  var chaiAsPromised = require('chai-as-promised');
  chai.use(chaiAsPromised);

  var expect = chai.expect;

  module.exports = function() {
    this.Given(/^I enter "([^"]*)" into the "([^"]*)" field$/, function(text, modelName, done) {
      element(by.model(modelName)).clear().sendKeys(text).then(done);
    });

    this.Given(/^I click the button with id "([^"]*)"$/, function(buttonId, done) {
      // express the regexp above with the code you wish you had
      element(by.id(buttonId)).click().then(done);
    });

    this.Then(/^the history table should contain a row with topic "([^"]*)"$/, function(topic, done) {
      var matchingTopics  = element.all(by.css('#competitions_table td.topic_cell')).filter(function(td){
        return td.getText().then(function(text){          
          return (text == topic);
        });
      });
      expect(matchingTopics).eventually.not.to.be.empty.and.notify(done);
    });


  };
})();
