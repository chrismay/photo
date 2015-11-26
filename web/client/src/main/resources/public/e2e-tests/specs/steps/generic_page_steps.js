
(function() {

  'use strict';
  //http://chaijs.com/
  var chai = require('chai');

  //https://github.com/domenic/chai-as-promised/
  var chaiAsPromised = require('chai-as-promised');
  chai.use(chaiAsPromised);

  var expect = chai.expect;

  module.exports = function() {


    this.Given(/^I go to "([^"]*)"$/, function(url, done) {
      browser.get('http://localhost:8080/app'+  url);
      done();
    });

    this.Then(/^the title should equal "([^"]*)"$/, function(title, done) {
      expect(browser.getTitle()).to.eventually.equal(title).and.notify(done);
    });

    this.Then(/^I should see a link with id "([^"]*)" with text "([^"]*)"$/, function(linkId, expectedLinkText, done) {

      var actualText = element(by.id(linkId)).getText();
      expect(actualText).to.eventually.equal(expectedLinkText).and.notify(done);

    });

    this.Then(/^the page heading should equal "([^"]*)"$/, function(expectedText, done) {
      var actualText = element(by.tagName('h1')).getText();
      expect(actualText).to.eventually.equal(expectedText).and.notify(done);
    });

  };
})();
