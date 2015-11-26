module.exports = function (config) {
    config.set({

        basePath: './',

        files: [
            'app/bower_components/angular/angular.js',
            'app/bower_components/angular-route/angular-route.js',
            'app/bower_components/angular-mocks/angular-mocks.js',
            'app/bower_components/ng-file-upload/ng-file-upload-all.js',
            'app/app.js',
            'app/components/**/*.js',
            'app/services/**/*.js',
            'app/current_competition/**/*.js',
            'app/history/**/*.js',
            'app/upload/**/*.js'

        ],

        autoWatch: true,

        frameworks: ['jasmine'],

        //browsers: ['Chrome'],
        browsers: ['PhantomJS'],

        plugins: [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-phantomjs-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
        ],

        junitReporter: {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        }

    });
};
