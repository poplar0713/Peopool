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
  devServer: {
    open: process.platform === "darwin",
    host: "0.0.0.0",
    port: 8444,
    https: {
      key: fs.readFileSync(".certs/key.pem"),
      cert: fs.readFileSync(".certs/cert.pem"),
    },
    hotOnly: false,

    // https: true,
    // port: 8444,
    // open: true,
    proxy: {
      "api.v1": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/webjars": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/group-call": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/upload": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/auth": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/ind": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/ent": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/fol": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/has": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/int": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/poe": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/poi": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
      "/taglist/": {
        target: "https://i5d206.p.ssafy.io:8443",
      },
    },
  },
};
