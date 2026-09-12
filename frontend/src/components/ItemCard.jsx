import { Link } from "react-router-dom";

export default function ItemCard({ item }) {
  const formatearPrecio = (precio) => {
    if (precio == null) return "—";
    return new Intl.NumberFormat("es-GT", {
      style: "currency",
      currency: "GTQ",
    }).format(precio);
  };

  const colorEstado = (status) => {
    switch (status) {
      case "AVAILABLE":     return { bg: "#d4edda", color: "#155724" };
      case "LOW_STOCK":     return { bg: "#fff3cd", color: "#856404" };
      case "OUT_OF_STOCK":  return { bg: "#f8d7da", color: "#721c24" };
      case "DISCONTINUED":  return { bg: "#e2e3e5", color: "#383d41" };
      default:              return { bg: "#e2e3e5", color: "#383d41" };
    }
  };

  const estado = colorEstado(item.status);

  return (
    <div style={styles.card}>
      <div style={styles.imagenContainer}>
        <img
          src={item.imageUrl}
          alt={item.title}
          style={styles.imagen}
          onError={(e) => {
            e.target.src = "https://via.placeholder.com/400x300?text=Sin+Imagen";
          }}
        />
        <span style={{ ...styles.badge, backgroundColor: estado.bg, color: estado.color }}>
          {item.status}
        </span>
      </div>

      <div style={styles.contenido}>
        <h3 style={styles.titulo}>{item.title}</h3>

        <div style={styles.infoRow}>
          <span style={styles.label}>Precio:</span>
          <span style={styles.precio}>{formatearPrecio(item.price)}</span>
        </div>

        <div style={styles.infoRow}>
          <span style={styles.label}>Stock:</span>
          <span>{item.stock} unidades</span>
        </div>

        <div style={styles.infoRow}>
          <span style={styles.label}>Rating:</span>
          <span>⭐ {item.rating ?? "—"}</span>
        </div>

        <div style={styles.scoreBox}>
          <span style={styles.scoreLabel}>Score</span>
          <span style={styles.scoreValor}>{item.score.toFixed(4)}</span>
        </div>

        <Link to={`/items/${item.id}`} style={styles.btnDetalle}>
          Ver detalle →
        </Link>
      </div>
    </div>
  );
}

const styles = {
  card: {
    border: "1px solid #ddd",
    borderRadius: "10px",
    overflow: "hidden",
    backgroundColor: "#fff",
    boxShadow: "0 2px 6px rgba(0,0,0,0.08)",
    display: "flex",
    flexDirection: "column",
    transition: "transform 0.15s, box-shadow 0.15s",
  },
  imagenContainer: {
    position: "relative",
    height: "180px",
    backgroundColor: "#f4f4f4",
  },
  imagen: {
    width: "100%",
    height: "100%",
    objectFit: "cover",
  },
  badge: {
    position: "absolute",
    top: "10px",
    right: "10px",
    padding: "4px 10px",
    borderRadius: "12px",
    fontSize: "11px",
    fontWeight: "bold",
  },
  contenido: {
    padding: "16px",
    display: "flex",
    flexDirection: "column",
    gap: "8px",
  },
  titulo: {
    margin: 0,
    fontSize: "17px",
    color: "#212529",
    minHeight: "44px",
  },
  infoRow: {
    display: "flex",
    justifyContent: "space-between",
    fontSize: "14px",
    color: "#555",
  },
  label: {
    color: "#888",
  },
  precio: {
    fontWeight: "bold",
    color: "#28a745",
  },
  scoreBox: {
    marginTop: "8px",
    padding: "8px 12px",
    backgroundColor: "#e7f3ff",
    border: "1px solid #b6dcff",
    borderRadius: "6px",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  },
  scoreLabel: {
    fontSize: "12px",
    fontWeight: "bold",
    color: "#0056b3",
    textTransform: "uppercase",
    letterSpacing: "0.5px",
  },
  scoreValor: {
    fontSize: "16px",
    fontWeight: "bold",
    color: "#0056b3",
    fontFamily: "monospace",
  },
  btnDetalle: {
    marginTop: "8px",
    padding: "8px 12px",
    backgroundColor: "#007bff",
    color: "#fff",
    textAlign: "center",
    borderRadius: "6px",
    textDecoration: "none",
    fontSize: "14px",
    fontWeight: "600",
  },
};