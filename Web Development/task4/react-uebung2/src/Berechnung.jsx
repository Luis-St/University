function Berechnung({ berechnung, loeschen }) {
  return (
    <div style={styles.zeile}>
      <span>
        {berechnung.zahl1} {berechnung.operator} {berechnung.zahl2} = {berechnung.ergebnis}
      </span>
      <button onClick={loeschen} style={styles.papierkorbBtn}>
        🗑
      </button>
    </div>
  )
}

const styles = {
  zeile: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: '10px',
    padding: '5px',
    border: '1px solid #ccc',
  },
  papierkorbBtn: {
    background: 'none',
    border: '1px solid #ccc',
    cursor: 'pointer',
    fontSize: '16px',
  },
}

export default Berechnung
