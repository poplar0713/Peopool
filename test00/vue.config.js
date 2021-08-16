const fs = require("fs");
// const webpack = require("webpack");

module.exports = {
  // outputDir: "../peopool/src/main/resources/static",
  // indexPath: "../static/index.html",
  // configureWebpack: {
  //   plugins: [
  //     new webpack.optimize.LimitChunkCountPlugin({
  //       maxChunks: 1,
  //     }),
  //   ],
  // },
  // filenameHashing: false,
  // productionSourceMap: false,
  configureWebpack: {
    devtool: "source-map",
  },
  devServer: {
    open: process.platform === "darwin",
    host: "0.0.0.0",
    port: 8444,
    https: {
      key: fs.readFileSync(".certs/key.pem"),
      cert: fs.readFileSync(".certs/cert.pem"),
    },
    hotOnly: false,
  },
};
