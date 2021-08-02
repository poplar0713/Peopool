module.exports = {
  // outputDir: "../peopool/src/main/resources/static",
  // indexPath: "../static/index.html",
  devServer: {
    https: true,
    port: 8444,
    open: true,
    proxy: {
      "api.v1": {
<<<<<<< HEAD
        target: "https://52.79.162.52:8443//",
      },
      "/webjars": {
        target: "https://52.79.162.52:8443//",
      },
      "/group-call": {
        target: "https://52.79.162.52:8443//",
      },
      "/upload": {
        target: "https://52.79.162.52:8443//",
      },
      "/auth": {
        target: "https://52.79.162.52:8443//",
      },
      "^/ind": {
        target: "https://52.79.162.52:8443//",
        changeOrigin: true,
=======
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
      "/auth": {
        target: "https://localhost:8443/",
>>>>>>> c5ba662e5b5277ed667749f4d16ce2af241b4fdf
      },
    },
  },
};
