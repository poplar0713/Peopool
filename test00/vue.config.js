module.exports = {
  devServer: {
    https: true,
    port: 8444,
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
      "/auth": {
        target: "https://localhost:8443/",
      },
    },
  },
};
