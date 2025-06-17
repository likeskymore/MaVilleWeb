import React from 'react';
import { useNavigate } from 'react-router-dom'; // if you use react-router for navigation

const IndexPage: React.FC = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gradient-to-r from-blue-500 to-indigo-600 text-white flex flex-col items-center justify-center px-4">
      <h1 className="text-5xl font-bold mb-6">Bienvenue à MaVille</h1>
      <p className="max-w-xl text-center mb-8 text-lg">
        Gérez facilement les travaux et entraves dans votre ville avec notre application simple et efficace.
      </p>


      <button
        onClick={() => navigate('/login')}  // or change to wherever your login page is
        className="bg-white text-blue-600 font-semibold px-6 py-3 rounded shadow hover:bg-gray-100 transition"
      >
        Se connecter
      </button>

      <button
        onClick={() => navigate('/sign-up')}  // or change to wherever your login page is
        className="bg-white text-blue-600 font-semibold px-6 py-3 rounded shadow hover:bg-gray-100 transition"
      >
        S'inscrire
      </button>

      <section className="mt-16 max-w-4xl text-center">
        <h2 className="text-3xl font-semibold mb-4">Pourquoi utiliser MaVille?</h2>
        <ul className="list-disc list-inside space-y-2 text-lg">
          <li>Consultez les travaux en cours dans votre quartier.</li>
          <li>Recevez des alertes en temps réel.</li>
          <li>Communiquez avec les intervenants responsables.</li>
          <li>Planifiez vos déplacements en évitant les entraves.</li>
        </ul>
      </section>
    </div>
  );
};

export default IndexPage;
