import React, { useState } from 'react';

const SignUpPage: React.FC = () => {
  const [accountType, setAccountType] = useState<'resident' | 'intervenant' | null>(null);
  const [form, setForm] = useState({
    name: '',
    birthDate: '',
    email: '',
    password: '',
    streetNumber: '',
    streetName: '',
    postalCode: '',
    intervenantType: '',
    cityId: ''
  });
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleAccountTypeSelect = (type: 'resident' | 'intervenant') => {
    setAccountType(type);
    setForm({
      name: '',
      birthDate: '',
      email: '',
      password: '',
      streetNumber: '',
      streetName: '',
      postalCode: '',
      intervenantType: '',
      cityId: ''
    });
    setError(null);
    setSuccess(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setSuccess(null);

    // Simulated backend call
    console.log("Sending form data: ", form);

    // Add real API POST request here (e.g. axios.post)
    setTimeout(() => {
      setSuccess("Compte créé avec succès !");
    }, 1000);
  };

  return (
    <div className="min-h-screen bg-gradient-to-r from-blue-500 to-indigo-600 text-white flex flex-col items-center justify-center px-4">
      <h1 className="text-4xl font-bold mb-4">Créer un compte</h1>

      {!accountType && (
        <div className="space-x-4">
          <button
            onClick={() => handleAccountTypeSelect('resident')}
            className="bg-white text-blue-600 font-semibold px-6 py-3 rounded shadow hover:bg-gray-100 transition"
          >
            Résident
          </button>
          <button
            onClick={() => handleAccountTypeSelect('intervenant')}
            className="bg-white text-blue-600 font-semibold px-6 py-3 rounded shadow hover:bg-gray-100 transition"
          >
            Intervenant
          </button>
        </div>
      )}

      {accountType && (
        <form
          onSubmit={handleSubmit}
          className="bg-white text-black p-6 mt-6 rounded-lg shadow-lg w-full max-w-md space-y-4"
        >
          <h2 className="text-2xl font-semibold text-center text-blue-600 mb-4">
            {accountType === 'resident' ? 'Inscription Résident' : 'Inscription Intervenant'}
          </h2>

          <input
            type="text"
            name="name"
            placeholder="Nom complet"
            value={form.name}
            onChange={handleInputChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />

          <input
            type="email"
            name="email"
            placeholder="Adresse email"
            value={form.email}
            onChange={handleInputChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />

          <input
            type="password"
            name="password"
            placeholder="Mot de passe"
            value={form.password}
            onChange={handleInputChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />

          {accountType === 'resident' && (
            <>
              <input
                type="date"
                name="birthDate"
                value={form.birthDate}
                onChange={handleInputChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
              <input
                type="text"
                name="streetNumber"
                placeholder="Numéro de rue"
                value={form.streetNumber}
                onChange={handleInputChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
              <input
                type="text"
                name="streetName"
                placeholder="Nom de rue"
                value={form.streetName}
                onChange={handleInputChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
              <input
                type="text"
                name="postalCode"
                placeholder="Code postal"
                value={form.postalCode}
                onChange={handleInputChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </>
          )}

          {accountType === 'intervenant' && (
            <>
              <input
                type="text"
                name="intervenantType"
                placeholder="Type d'intervenant (ex: Police, Voirie)"
                value={form.intervenantType}
                onChange={handleInputChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
              <input
                type="text"
                name="cityId"
                placeholder="ID de ville (8 chiffres)"
                value={form.cityId}
                onChange={handleInputChange}
                required
                pattern="\d{8}"
                className="w-full p-2 border border-gray-300 rounded"
              />
            </>
          )}

          {error && <p className="text-red-600">{error}</p>}
          {success && <p className="text-green-600">{success}</p>}

          <div className="flex justify-between">
            <button
              type="button"
              onClick={() => setAccountType(null)}
              className="text-sm text-gray-600 underline"
            >
              Retour
            </button>
            <button
              type="submit"
              className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
            >
              Créer un compte
            </button>
          </div>
        </form>
      )}
    </div>
  );
};

export default SignUpPage;
