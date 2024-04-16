import React, { useState, useMemo } from 'react';

const FactorialCalculator = () => {
    const [number, setNumber] = useState(5);

    const factorial = useMemo(() => {
        console.log('Calculating factorial...');
        let result = 1;
        for (let i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }, [number]);

    return (
        <div>
            <h2>Factorial Calculator</h2>
            <input
                type="number"
                value={number}
                onChange={(e) => setNumber(parseInt(e.target.value))}
            />
            <p>Factorial of {number} is: {factorial}</p>
        </div>
    );
};

export default FactorialCalculator;