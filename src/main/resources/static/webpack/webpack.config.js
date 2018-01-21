module.exports = {
	entry: "./jsx/user/collaboration/entry.js",
    output: {
        path: __dirname + "/jsx/user/collaboration",
        filename: "bundle.js"
    },
    module: {
        loaders: [
            { 
            	test: /.jsx?$/, 
            	loader: 'babel-loader',
            	exclude: /node_modules/,
            	query: {
            		presets: [
            			"react",
            			"es2015"
            		]
            	}
            }
        ]
    }
};

