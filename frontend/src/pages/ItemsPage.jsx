import { useEffect, useState } from "react";
import ItemList from "../components/ItemList";
import itemService from "../services/itemService";

export default function ItemsPage() {
  const [items, setItems] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  const cargarItems = () => {
    setCargando(true);
    setError(null);
    itemService
      .listarTodos()
      .then((data) => setItems(data))
      .catch((err) => setError(err.message))
      .finally(() => setCargando(false));
  };

  useEffect(() => {
    cargarItems();
  }, []);

  return (
    <div style={styles.container}>
      <header style={styles.header}>
        <div>
          <h1 style={styles.titulo}>🛒 Catálogo Express</h1>
          <p style={styles.subtitulo}>
            Items ordenados por score descendente
          </p>
        </div>
        <button onClick={cargarItems} disabled={cargando} style={styles.btnRefrescar}>
          🔄 Refrescar
        </button>
      </header>

      <ItemList items={items} cargando={cargando} error={error} />
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "1200px",
    margin: "0 auto",
    padding: "24px",
  },
  header: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "flex-start",
    marginBottom: "20px",
    borderBottom: "2px solid #e9ecef",
    paddingBottom: "16px",
  },
  titulo: {
    margin: 0,
    fontSize: "28px",
    color: "#212529",
  },
  subtitulo: {
    margin: "4px 0 0",
    color: "#6c757d",
    fontSize: "14px",
  },
  btnRefrescar: {
    padding: "8px 16px",
    backgroundColor: "#17a2b8",
    color: "#fff",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    fontSize: "14px",
    fontWeight: "600",
  },
};