using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MazesAndMore
{
    public class MazeManager : MonoBehaviour
    {
        /*
        * Pos del jugador
        * Pistas que das al jugador
        * Se da cuenta si ha llegado al final el player
         */

        public Tile tilePrefab;

        public void SetMap(Map map)
        {

        }

        public void Reset()
        {

        }

        public void Init()
        {

        }

        private Tile[,] _tiles;
        private LevelManager _levelManager;
    }
}
