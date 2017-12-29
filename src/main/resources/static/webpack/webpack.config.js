module.exports = {
	entry: "./jsx/admin/project/entry.js",
    output: {
        path: __dirname + "/jsx/admin/project",
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

