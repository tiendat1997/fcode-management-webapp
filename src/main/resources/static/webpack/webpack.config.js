module.exports = {
	entry: "./jsx/admin/event/entry.js",
    output: {
        path: __dirname + "/jsx/admin/event",
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

