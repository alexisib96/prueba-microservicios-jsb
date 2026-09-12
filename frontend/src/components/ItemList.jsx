import ItemCard from "./ItemCard";

export default function ItemList({ items, cargando, error }) {
  if (cargando) {
    return (
      <div style={styles.estadoContainer}>
        <div style={styles.spinner}></div>
        <p style={styles.mensaje}>Cargando catálogo...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div style={{ ...styles.estadoContainer, color: "#721c24" }}>
        <p style={{ fontSize: "32px", margin: 0 }}>⚠️</p>
        <p style={styles.mensaje}>{error}</p>
      </div>
    );
  }

  if (!items || items.length === 0) {
    return (
      <div style={styles.estadoContainer}>
        <p style={{ fontSize: "32px", margin: 0 }}>📭</p>
        <p style={styles.mensaje}>No hay items en el catálogo.</p>
      </div>
    );
  }

  return (
    <div style={styles.grid}>
      {items.map((item) => (
        <ItemCard key={item.id} item={item} />
      ))}
    </div>
  );
}

const styles = {
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))",
    gap: "20px",
    marginTop: "20px",
  },
  estadoContainer: {
    textAlign: "center",
    padding: "60px 20px",
    color: "#666",
  },
  mensaje: {
    fontSize: "16px",
    marginTop: "12px",
  },
  spinner: {
    width: "40px",
    height: "40px",
    margin: "0 auto",
    border: "4px solid #f3f3f3",
    borderTop: "4px solid #007bff",
    borderRadius: "50%",
    animation: "spin 1s linear infinite",
  },
};