module.exports = {
  // outputDir: "../peopool/src/main/resources/static",
  // indexPath: "../static/index.html",
  devServer: {
    // https: false,
    port: 8444,
    open: true,
    proxy: {
      "api.v1": {
        target: "http://52.79.162.52:8443/",
      },
      "/webjars": {
        target: "http://52.79.162.52:8443/",
      },
      "/group-call": {
        target: "http://52.79.162.52:8443/",
      },
      "/upload": {
        target: "http://52.79.162.52:8443/",
      },
      "/auth": {
        target: "http://52.79.162.52:8443/",
      },
    },
  },
};

