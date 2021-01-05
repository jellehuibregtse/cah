import React, { useEffect, useState } from 'react';
import {
  Stack,
  FormControl,
  Image,
  Badge,
  Input,
  InputGroup,
  InputLeftElement,
  Center,
  Text,
  Box,
  SimpleGrid,
  Button,
} from '@chakra-ui/react';

import { SearchIcon, ArrowForwardIcon } from '@chakra-ui/icons';
import GameSearchCard from './GameSearchCard';

export default function GameSearch() {
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(false);
  const [games, setGames] = useState([
    {
      started: true,
      players: 5,
      maxPlayers: 10,
      rounds: 10,
      currentRound: 1,
      creator: 'Player1'
    },
    {
      started: false,
      players: 1,
      maxPlayers: 10,
      rounds: 10,
      currentRound: 0,
      creator: 'Player2'
    },
    {
      started: false,
      players: 3,
      maxPlayers: 10,
      rounds: 10,
      currentRound: 0,
      creator: 'Player3'
    }
    ,{
      started: true,
      players: 8,
      maxPlayers: 10,
      rounds: 10,
      currentRound: 10,
      creator: 'Player4'
    }
  ]);

  useEffect(() => {
    getGames();
  }, []);

  const getGames = async () => {
    setLoading(true);
    // fetch games
    setLoading(false);
  };

  const handleSearch = (event) => {
    event.preventDefault();

  };

  return (
    <Box mt='2rem'>
      <Center>
        <form method='POST' onSubmit={handleSearch}>
          <Stack minWidth={600} maxWidth={600} margin='1rem' spacing={5}>
            <Text fontSize='3xl'><strong>Current games</strong></Text>
            <FormControl>
              <InputGroup>
                <InputLeftElement>
                  <SearchIcon color='gray.300' />
                </InputLeftElement>
                <Input
                  id='search'
                  value={search}
                  placeholder='Search for a game'
                  onChange={({ target }) => setSearch(target.value)}
                />
              </InputGroup>
            </FormControl>
          </Stack>
        </form>
      </Center>


      <SimpleGrid m='6' columns={3} spacing={5}>
        {games.map(game =>
          <GameSearchCard
            started={game.started}
            players={game.players}
            maxPlayers={game.maxPlayers}
            currentRound={game.currentRound}
            rounds={game.rounds}
            creator={game.creator}/>,
        )}
      </SimpleGrid>
    </Box>
  );
}