// AcronymSearch.js

import React, { useState } from 'react';

const AcronymSearch = () => {
  const [acronym, setAcronym] = useState('');
  const [result, setResult] = useState([]);

  const handleInputChange = (event) => {
    setAcronym(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    console.log('Form submitted');

    try {
      const response = await fetch(`http://localhost:8080/lyric-finder/search/${acronym}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }

      const data = await response.json();
      setResult(data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Enter Acronym:
          <input type="text" value={acronym} onChange={handleInputChange} />
        </label>
        <button type="submit">Search</button>
      </form>

      <div>
        <h2>Result:</h2>
        <ul>
          {result.map((item, index) => (
            <li key={index}>{item}</li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default AcronymSearch;
