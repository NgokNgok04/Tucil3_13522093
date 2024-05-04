function SearchButton({wordToSearch,handleRespond}) {


    const handleClick = async () => {
        try {
            const response = await fetch('http://localhost:8000', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    startWord : wordToSearch.startWord,
                    endWord : wordToSearch.endWord,
                    algorithmType : wordToSearch.algorithmType,
                }),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const tempResponse = await response.json();
            handleRespond({
                timeExecution : tempResponse.timeExecution,
                nodeVisited : tempResponse.nodeVisited,
                len : tempResponse.len,
                solution : tempResponse.solution,
            })
        } catch (error) {
            console.error('Error:', error);
        }
    };

  return (
    <button 
        className="flex bg-[#68649c] text-center border-black border-[3px] px-8 py-4 rounded-full font-gingerCat text-white shadowButton"
        onClick={handleClick}
    >
        SEARCH
    </button>
  )
}

export default SearchButton