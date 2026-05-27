import React from 'react'

// Aufgabe 1: Webseite mit React.createElement() (kein JSX)

function App() {
  return React.createElement(
    'div',
    null,

    React.createElement('h1', null, 'Titel'),

    React.createElement(
      'p',
      null,
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
    ),

    React.createElement(
      'div',
      null,
      React.createElement('h3', null, 'Liste'),
      React.createElement(
        'ul',
        null,
        React.createElement('li', null, 'Tisch'),
        React.createElement('li', null, 'Stuhl'),
        React.createElement('li', null, 'Herd')
      )
    ),

    React.createElement(
      'div',
      null,
      React.createElement('h3', null, 'Tabelle'),
      React.createElement(
        'table',
        { style: { backgroundColor: 'green', border: '1px solid black' } },
        React.createElement(
          'thead',
          null,
          React.createElement(
            'tr',
            null,
            React.createElement('th', { style: { border: '1px solid black' } }, 'Nr'),
            React.createElement('th', { style: { border: '1px solid black' } }, 'Info')
          )
        ),
        React.createElement(
          'tbody',
          null,
          React.createElement(
            'tr',
            null,
            React.createElement('td', { style: { border: '1px solid black' } }, '1'),
            React.createElement('td', { style: { border: '1px solid black' } }, 'Info 1')
          ),
          React.createElement(
            'tr',
            null,
            React.createElement('td', { style: { border: '1px solid black' } }, '2'),
            React.createElement('td', { style: { border: '1px solid black' } }, 'Info 2')
          )
        )
      )
    )
  )
}

export default App
