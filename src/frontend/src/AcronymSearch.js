// AcronymSearch.js

import React, { useState } from 'react';
import './AcronymSearch.css';

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
        <div className="acronym-search-container">

            <div className="left-column">
                <div className="eras-container">
                    <div className="eras-column">
                        <div className="eras-square" id="square1"></div>
                        <div className="eras-square" id="square2"></div>
                        <div className="eras-square" id="square3"></div>
                        <div className="eras-square" id="square4"></div>
                    </div>
                    <div className="eras-column">
                        <div className="eras-square" id="square5"></div>
                        <div className="eras-square" id="square6">the taylor</div>
                        <div className="eras-square" id="square7">swift lyric</div>
                        <div className="eras-square" id="square8">finder</div>                        
                    </div>
                    <div className="eras-column">
                        <div className="eras-square" id="square9"></div>
                        <div className="eras-square" id="square10"></div>
                        <div className="eras-square" id="square11"></div>
                        <div className="eras-square" id="square12"></div>
                    </div>
                </div>

                <div className="search-container">
                    <form onSubmit={handleSubmit} className="acronym-search-form">
                        <label>
                            enter acronym:
                            <input type="text" 
                                value={acronym} 
                                onChange={handleInputChange} 
                                placeholder="acronym (i.e. iwbyilmf)"/>
                        </label>
                        <button type="submit" className="search-button">search lyrics</button>
                    </form>
                </div>
            </div>


            <div className="result-container">
                <div className="matches-heading">lyrical matches:</div>
                <div className="results-list">
                    <ul>
                        {result.map((item, index) => (
                            <li key={index}>{item}</li>
                        ))}
                    </ul>
                </div>

            </div>
        </div>
    );
};

export default AcronymSearch;
