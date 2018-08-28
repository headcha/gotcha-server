module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        cssmin: {
            options: {
                mergeIntoShorthands: false,
                roundingPrecision: -1
            },
            target: {
                files: {
                    './src/main/resources/public/dist/css/merge-all.min.css': ['./src/main/resources/public/dist/css/merge-all.css']
                }
            }
        },

        uglify: {
            options: {
                sourceMap: false,

                compress: {
                    global_defs: {
                        'DEBUG': false
                    },
                    dead_code: true
                }
            },
            my_target: {
                files: {
                    './src/main/resources/public/dist/js/merge-all.min.js': ['./src/main/resources/public/dist/js/merge-all.js']
                }
            }
        },
        //concat 설정
        concat: {
            options: {
                sourceMap: false
            },
            js: {
                src: ['./src/main/resources/public/lib/bootstrap/html/template/plugins/jquery.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/bootstrap/js/bootstrap.bundle.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/rs-plugin-5/js/jquery.themepunch.tools.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/rs-plugin-5/js/source/jquery.themepunch.revolution.js',

                    './src/main/resources/public/lib/bootstrap/html/template/plugins/fullpage/jquery.fullpage.js',
                    './src/main/resources/public/lib/bootstrap/vaildate/bootstrap-validator.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/isotope/imagesloaded.pkgd.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/isotope/isotope.pkgd.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/magnific-popup/jquery.magnific-popup.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/waypoints/jquery.waypoints.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/waypoints/sticky.min.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/countTo/jquery.countTo.js',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/slick/slick.min.js',

                    './src/main/resources/public/lib/bootstrap/datetime/moment.js',
                    './src/main/resources/public/lib/bootstrap/datetime/locale/ko.js',

                    './src/main/resources/public/js/util/AjaxUtil.js',



                    './src/main/resources/public/lib/cloudinary/cloudinary-core-shrinkwrap.js',
                    './src/main/resources/public/lib/bootstrap/html/template/js/template.js',


                    './src/main/resources/public/lib/template/handlebars.v4.0.5.js',
                    './src/main/resources/public/lib/template/handlebars-intl.js',
                    './src/main/resources/public/lib/template/handlebars.helper.js',
                    './src/main/resources/public/lib/bootstrap/html/template/js/custom.js'],

                dest: './src/main/resources/public/dist/js/merge-all.js' //concat 결과 파일
            },
            css: {
                src: [
                    './src/main/resources/public/lib/bootstrap/html/template/css/animations.css',
                    './src/main/resources/public/lib/bootstrap/html/template/bootstrap/css/bootstrap.css',
                    './src/main/resources/public/lib/bootstrap/html/template/fonts/font-awesome/css/font-awesome.css',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/magnific-popup/magnific-popup.css',


                    './src/main/resources/public/lib/bootstrap/html/template/plugins/rs-plugin-5/css/settings.css',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/rs-plugin-5/css/navigation.css',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/fullpage/jquery.fullpage.min.css',
                    './src/main/resources/public/lib/bootstrap/html/template/plugins/rs-plugin-5/css/settings.css',



                    './src/main/resources/public/lib/bootstrap/html/template/plugins/slick/slick.css',
                    './src/main/resources/public/lib/bootstrap/html/template/css/style.css',
                    './src/main/resources/public/lib/bootstrap/html/template/css/typography-default.css',
                    './src/main/resources/public/lib/bootstrap/html/template/css/skins/light_blue.css',
                    './src/main/resources/public/lib/bootstrap/html/template/css/custom.css',
                ],
                dest: './src/main/resources/public/dist/css/merge-all.css'
            }
        }
    });

    // Load the plugin that provides the "uglify", "concat" tasks.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-cssmin');

    // Default task(s).
    grunt.registerTask('default', ['concat', 'uglify' , 'cssmin']); //grunt 명령어로 실행할 작업

};