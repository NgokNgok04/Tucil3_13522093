function SearchInput({type,handleWord}) {
    let value;
    const handleChange = async (e) => {
        if (e !== null && e !== undefined && e !== '') {
            value = e.target.value;
            if (type === "Start"){
                handleWord(prevState => {
                    return {
                        ...prevState,
                        startWord : value,
                    }
                
                })
            } else if (type === "End"){
                handleWord(prevState => {
                    return {
                        ...prevState,
                        endWord : value,
                    }
                })
            }
        }
    }


    return (
        <div className="flex flex-col">
            <input 
                type="text" 
                placeholder={type}
                value = {value}
                className="bg-[#8884bc] text-center w-full p-4 rounded-md border-black border-[3px] focus:outline-none placeholder-center placeholder-white text-[#f8ec34] text-2xl font-gingerCat shadowButton"
                onChange={handleChange}
                required
            />
        </div>
  );
}

export default SearchInput;
