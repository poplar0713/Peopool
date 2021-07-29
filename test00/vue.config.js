module.exports = {
  outputDir: "../peopool/src/main/resources/static",
  indexPath: "../static/index.html",
  devServer: {
    https: true,
    port: 8443,
    open: true,
    proxy: {
      "api.v1": {
        target: "https://localhost:8443/",
      },
      "/webjars": {
        target: "https://localhost:8443/",
      },
      "/group-call": {
        target: "https://localhost:8443/",
      },
      "/upload": {
        target: "https://localhost:8443/",
      },
<<<<<<< HEAD
      "/ind": {
=======
      "/auth": {
>>>>>>> 6d5f31f6ba1d47e64438d2220e3772795da394fa
        target: "https://localhost:8443/",
      },
    },
  },
};

