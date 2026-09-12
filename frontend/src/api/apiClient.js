import axios from "axios";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "/api",
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

apiClient.interceptors.request.use(
  (config) => {
    if (import.meta.env.DEV) {
      console.log(`🚀 ${config.method.toUpperCase()} ${config.url}`);
    }
    return config;
  },
  (error) => Promise.reject(error)
);

apiClient.interceptors.response.use(
  (response) => {
    if (import.meta.env.DEV) {
      console.log(`✅ ${response.status} ${response.config.url}`);
    }
    return response.data;
  },
  (error) => {
    const customError = { message: "Error desconocido", status: null };

    if (error.response) {
      const { status, data } = error.response;
      customError.status = status;
      customError.message = data.message || data.error || `Error ${status}`;
    } else if (error.request) {
      customError.message = "No se pudo conectar con el servidor.";
    } else {
      customError.message = error.message;
    }

    console.error("❌ Error API:", customError);
    return Promise.reject(customError);
  }
);

export default apiClient;