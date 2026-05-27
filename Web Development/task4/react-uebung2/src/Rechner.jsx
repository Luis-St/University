import { useState } from 'react'
import Berechnung from './Berechnung'

function Rechner() {
  const [zahl1, setZahl1] = useState(0)
  const [zahl2, setZahl2] = useState(0)
  const [operator, setOperator] = useState('+')
  const [letzteBerechnung, setLetzteBerechnung] = useState(null)

  function berechnen() {
    const a = parseFloat(zahl1)
    const b = parseFloat(zahl2)
    let ergebnis
    switch (operator) {
      case '+': ergebnis = a + b; break
      case '-': ergebnis = a - b; break
      case '*': ergebnis = a * b; break
      case '/': ergebnis = b !== 0 ? a / b : 'Fehler (Division durch 0)'; break
      default:  ergebnis = 0
    }
    setLetzteBerechnung({ zahl1: a, operator, zahl2: b, ergebnis })
  }

  return (
    <div style={styles.container}>
      <h2 style={styles.titel}>Rechner</h2>

      <div style={styles.inhalt}>
        <div style={styles.eingabeZeile}>
          <input
            type="number"
            value={zahl1}
            onChange={(e) => setZahl1(e.target.value)}
            style={styles.input}
          />
          <select value={operator} onChange={(e) => setOperator(e.target.value)}>
            <option value="+">+</option>
            <option value="-">-</option>
            <option value="*">*</option>
            <option value="/">/</option>
          </select>
          <input
            type="number"
            value={zahl2}
            onChange={(e) => setZahl2(e.target.value)}
            style={styles.input}
          />
          {letzteBerechnung !== null && (
            <span>= {letzteBerechnung.ergebnis}</span>
          )}
        </div>

        <button onClick={berechnen}>Berechnen</button>

        {letzteBerechnung !== null && (
          <Berechnung
            berechnung={letzteBerechnung}
            loeschen={() => setLetzteBerechnung(null)}
          />
        )}
      </div>
    </div>
  )
}

const styles = {
  container: {
    border: '2px solid #333',
    width: '600px',
    margin: '20px auto',
  },
  titel: {
    textAlign: 'center',
    backgroundColor: '#2d3748',
    color: 'white',
    padding: '15px',
    margin: 0,
  },
  inhalt: {
    padding: '20px',
  },
  eingabeZeile: {
    display: 'flex',
    alignItems: 'center',
    gap: '10px',
    marginBottom: '8px',
  },
  input: {
    width: '120px',
  },
}

export default Rechner
