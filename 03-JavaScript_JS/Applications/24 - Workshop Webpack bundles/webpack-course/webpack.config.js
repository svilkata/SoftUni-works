const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'development',
    entry: path.resolve(__dirname, 'src/app.js'),  //стартирай с левия параметър (__dirname е текущата директория) и добави към него десния, и изведи абсолютния път 
    entry: {
        app: [
            './app.js',
            './static/layout.css',
            './static/modal.css',
            './static/site.css'
        ]
    },
    output: {
        filename: '[name].js',           //запиши със съответното име - понеже на няколко файла ги прави
        path: path.resolve(__dirname, 'dist'),
    },
    devServer: {
        port: 9000,
        publicPath: 'dist',
        writeToDisk: true,
        contentBase: path.resolve(__dirname, 'dist'),
        compress: true,
        historyApiFallback: {
            index: '/',
            disableDotRule: true,
        }
    },
    optimization: {
        splitChunks: {
            chunks: 'all'
        }
    },
    devtool: 'eval-source-map',
module: {
    rules: [
        {
            test: /\.css$/,
            exclude: /node_modules/,
            // loader: "style-loader",
            use: ['style-loader', 'css-loader']
        },
        {
            test: /\.(svg||ttf||png||jpg||gif)$/i,
            exclude: /node_modules/,
            type: 'asset',       //преценява как да го импортира
        }
    ]
},
    plugins: [
        // Add your plugins here
        // Learn more about plugins from https://webpack.js.org/configuration/plugins 
        new HtmlWebpackPlugin({
            inject: true,
            template: 'index.html',      //вземи този index.html от главната директория, инжектирай му app.js скриптове заедно с html И css скриптове
        }),
    ],
};

    // module: {
    //     rules: [
    //         {
    //             test: /\.m?js$/,
    //             exclude: /node_modules/,
    //             use: {
    //                 loader: 'babel-loader',
    //                 options: {
    //                   presets: ['@babel/preset-env']
    //                 }
    //             }
    //         }
    //     ]
    // }
