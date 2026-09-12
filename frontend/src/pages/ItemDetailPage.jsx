import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import itemService from "../services/itemService";

export default function ItemDetailPage() {
  const { id } = useParams();
  const [item, setItem] = useState(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setCargando(true);
    setError(null);
    itemService
      .obtenerPorId(id)
      .then((data) => setItem(data))
      .catch((err) => setError(err.message))
      .finally(() => setCargando(false));
  }, [id]);

  const formatearPrecio = (precio) => {
    if (precio == null) return "—";
    return new Intl.NumberFormat("es-GT", {
      style: "currency",
      currency: "GTQ",
    }).format(precio);
  };

  if (cargando) {
    return (
      <div style={styles.container}>
        <p style={styles.mensaje}>⏳ Cargando detalle...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div style={styles.container}>
        <Link to="/" style={styles.volver}>← Volver al listado</Link>
        <div style={styles.errorBox}>
          <p style={{ fontSize: "32px", margin: 0 }}>⚠️</p>
          <p>{error}</p>
        </div>
      </div>
    );
  }

  return (
    <div style={styles.container}>
      <Link to="/" style={styles.volver}>← Volver al listado</Link>

      <div style={styles.card}>
        <div style={styles.imagenCol}>
          <img
            src={item.imageUrl}
            alt={item.title}
            style={styles.imagen}
            onError={(e) => {
              e.target.src = "https://via.placeholder.com/600x400?text=Sin+Imagen";
            }}
          />
        </div>

        <div style={styles.infoCol}>
          <h1 style={styles.titulo}>{item.title}</h1>
          <p style={styles.descripcion}>
            {item.description || "Sin descripción disponible."}
          </p>

          <div style={styles.grid}>
            <div style={styles.infoBox}>
              <span style={styles.infoLabel}>Precio</span>
              <span style={styles.infoValor}>{formatearPrecio(item.price)}</span>
            </div>
            <div style={styles.infoBox}>
              <span style={styles.infoLabel}>Rating</span>
              <span style={styles.infoValor}>⭐ {item.rating ?? "—"}</span>
            </div>
            <div style={styles.infoBox}>
              <span style={styles.infoLabel}>Stock</span>
              <span style={styles.infoValor}>{item.stock} unidades</span>
            </div>
            <div style={styles.infoBox}>
              <span style={styles.infoLabel}>Estado</span>
              <span style={styles.infoValor}>{item.status}</span>
            </div>
          </div>

          <div style={styles.scoreBox}>
            <span style={styles.scoreLabel}>Score</span>
            <span style={styles.scoreValor}>{item.score.toFixed(4)}</span>
          </div>
        </div>
      </div>
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "1000px",
    margin: "0 auto",
    padding: "24px",
  },
  volver: {
    display: "inline-block",
    marginBottom: "16px",
    color: "#007bff",
    textDecoration: "none",
    fontSize: "14px",
  },
  card: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr",
    gap: "24px",
    backgroundColor: "#fff",
    borderRadius: "10px",
    overflow: "hidden",
    boxShadow: "0 2px 10px rgba(0,0,0,0.1)",
  },
  imagenCol: {
    backgroundColor: "#f4f4f4",
    minHeight: "400px",
  },
  imagen: {
    width: "100%",
    height: "100%",
    objectFit: "cover",
  },
  infoCol: {
    padding: "28px",
    display: "flex",
    flexDirection: "column",
    gap: "16px",
  },
  titulo: {
    margin: 0,
    fontSize: "26px",
    color: "#212529",
  },
  descripcion: {
    color: "#555",
    lineHeight: "1.6",
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr",
    gap: "12px",
  },
  infoBox: {
    padding: "10px",
    backgroundColor: "#f8f9fa",
    borderRadius: "6px",
    display: "flex",
    flexDirection: "column",
    gap: "4px",
  },
  infoLabel: {
    fontSize: "12px",
    color: "#888",
    textTransform: "uppercase",
    letterSpacing: "0.5px",
  },
  infoValor: {
    fontSize: "16px",
    fontWeight: "600",
    color: "#212529",
  },
  scoreBox: {
    marginTop: "8px",
    padding: "14px 18px",
    backgroundColor: "#e7f3ff",
    border: "1px solid #b6dcff",
    borderRadius: "8px",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  },
  scoreLabel: {
    fontSize: "14px",
    fontWeight: "bold",
    color: "#0056b3",
    textTransform: "uppercase",
    letterSpacing: "1px",
  },
  scoreValor: {
    fontSize: "24px",
    fontWeight: "bold",
    color: "#0056b3",
    fontFamily: "monospace",
  },
  mensaje: {
    textAlign: "center",
    padding: "60px",
    fontSize: "16px",
    color: "#666",
  },
  errorBox: {
    textAlign: "center",
    padding: "40px",
    backgroundColor: "#f8d7da",
    color: "#721c24",
    borderRadius: "8px",
    marginTop: "20px",
  },
};