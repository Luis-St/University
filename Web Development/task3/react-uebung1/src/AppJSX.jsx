// Aufgabe 2: Gleiche Webseite mit JSX

function AppJSX() {
  return (
    <div>
      <h1>Titel</h1>

      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat.
      </p>

      <div>
        <h3>Liste</h3>
        <ul>
          <li>Tisch</li>
          <li>Stuhl</li>
          <li>Herd</li>
        </ul>
      </div>

      <div>
        <h3>Tabelle</h3>
        <table style={{ backgroundColor: 'green', border: '1px solid black' }}>
          <thead>
            <tr>
              <th style={{ border: '1px solid black' }}>Nr</th>
              <th style={{ border: '1px solid black' }}>Info</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style={{ border: '1px solid black' }}>1</td>
              <td style={{ border: '1px solid black' }}>Info 1</td>
            </tr>
            <tr>
              <td style={{ border: '1px solid black' }}>2</td>
              <td style={{ border: '1px solid black' }}>Info 2</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default AppJSX
