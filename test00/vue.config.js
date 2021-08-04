

module.exports = {
  // outputDir: "../peopool/src/main/resources/static",
  // indexPath: "../static/index.html",
  devServer: {
    https: true,
    port: 8444,
    open: true,
    proxy: {
      "api.v1": {
        target: "https://52.79.162.52:8443/",
      },
      "/webjars": {
        target: "https://52.79.162.52:8443/",
      },
      "/group-call": {
        target: "https://52.79.162.52:8443/",
      },
      "/upload": {
        target: "https://52.79.162.52:8443/",
      },
      "/auth": {
        target: "https://52.79.162.52:8443/",
      },
      "/ind": {
        target: "https://52.79.162.52:8443/",
      },
      "/ent": {
        target: "https://52.79.162.52:8443/",
      },
      "/fol": {
        target: "https://52.79.162.52:8443/",
      },
      "/has": {
        target: "https://52.79.162.52:8443/",
      },
      "/int": {
        target: "https://52.79.162.52:8443/",
      },
      "/poe": {
        target: "https://52.79.162.52:8443/",
      },
      "/poi": {
        target: "https://52.79.162.52:8443/",
      },
      "/taglist/": {
        target: "https://52.79.162.52:8443/",
      },
    },
  },
};
