import { useState } from "react";
import Header from "./components/Header"
import Home from "./components/Home";
import SearchButton from "./components/SearchButton";
import SearchInput from "./components/SearchInput";
import Swal from "sweetalert2";
function App() {
  const [word,setWord] = useState({
    startWord : '',
    endWord : '',
    algorithmType : 'GBFS',
  })
  const [result,setResult] = useState({
    timeExecution : 0,
    nodeVisited : 0,
    len : 0,
    solution : [],
  })
  return (
    <div className="h-screen bg-[#68649c]">
      {console.log(word)}
      <main>
        <Header />
        <Home />
        <div className="gap-10">
          <div className="mt-10 flex flex-row gap-10 justify-center items-center">
            <SearchInput type="Start" handleWord={setWord}/>
            <h1 className="font-spaceComics text-[#f8ec34] text-4xl">TO</h1>
            <SearchInput type="End" handleWord={setWord}/>
          </div>
          <div className="mt-20 mb-10 flex justify-center items-center">
            <SearchButton wordToSearch={word} handleRespond={setResult}/>
          </div>
        </div>

      </main>
    </div>
  );
}

export default App;