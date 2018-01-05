module.exports = {
	entry: "./jsx/user/event/upcoming/entry.js",
    output: {
        path: __dirname + "/jsx/user/event/upcoming",
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

