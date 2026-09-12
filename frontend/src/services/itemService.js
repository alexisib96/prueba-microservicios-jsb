import apiClient from "../api/apiClient";

const BASE_URL = "/items";

const itemService = {
  listarTodos: () => apiClient.get(BASE_URL),
  obtenerPorId: (id) => apiClient.get(`${BASE_URL}/${id}`),
};

export default itemService;