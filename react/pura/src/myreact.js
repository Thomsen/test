import { useRef } from 'react';

const useMemo = (factory, deps) => {
    const cache = useRef({});

    if (!deepCompare(cache.current.deps, deps)) {
        console.log("recomputing...");
        cache.current.value = factory();
        cache.current.deps = deps;
    } else {
        console.log("use memo...");
    }

    return cache.current.value;
};

// compare value
const deepCompare = (arr1, arr2) => {
    if (!arr1 || !arr2) {
        return false;
    }

    if (arr1.length !== arr2.length) {
        return false;
    }
    
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) {
            return false;
        }
    }
    
    return true;
};

export default useMemo;


export const useAdd = (factory, deps) => {
    return useMemo(() => factory(), deps);
};
