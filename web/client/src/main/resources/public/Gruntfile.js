'use strict';

module.exports = function (grunt) {

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);
    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Configurable paths for the application
    var appConfig = {
        app: 'app'
    };
    grunt.loadNpmTasks('grunt-connect-proxy'); // Proxy support


    grunt.initConfig({

        appConfig: appConfig,

        // Protractor settings
        protractor: {
            all: {
                options: {
                    configFile: "e2e-tests/protractor-conf.js",
                }
            },
        },

        connect: {
            server: {
                options: {
                    base: appConfig.app,
                    keepalive: true,
                    port: 9000,
                    middleware: function (connect, options) {
                        var proxy = require('grunt-connect-proxy/lib/utils').proxyRequest;
                        return [
                            // Include the proxy first
                            proxy,
                            connect.static(options.base),
                            connect.directory(options.base)
                        ];
                    }
                }
            },
            proxies: [
                {
                    context: '/competition',
                    host: 'localhost',
                    port: 8080,
                    https: false,
                    xforward: false,
                    hideHeaders: ['x-removed-header']
                },
                {
                    context: '/photo',
                    host: 'localhost',
                    port: 8080,
                    https: false,
                    xforward: false,
                    hideHeaders: ['x-removed-header']
                }
            ]
        },

        jshint: {
            prod: {
                src: [
                    'Gruntfile.js',
                    'app/current_competition/*.js',
                    'app/components/*.js'
                ],
                options: {
                    jshintrc: './.jshintrc'
                }
            },
            test: {
                src: ['e2e-tests/**/*.js'],
                options: {
                    jshintrc: 'e2e-tests/.jshintrc'
                }
            }
        }/*,

        exec: {
            build_style: {
                command: "npm install",
                cwd: "./style"
            }
            /*,
             copy_style: {
             command: "cp -r fonts dist js less ../src/main/resources/static/",
             cwd: "./style"
             }
        }*/

    });

    grunt.registerTask('serve', [
        'configureProxies:server',
        'connect:server'
    ]);

    grunt.registerTask('e2e', ['selenium_start', 'protractor', 'selenium_stop']);

    grunt.registerTask('default', ['jshint', 'exec']);
};
